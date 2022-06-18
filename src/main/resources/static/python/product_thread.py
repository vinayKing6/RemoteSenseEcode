from socket import *
import os
from paddlers.deploy import Predictor
import numpy as np
from PIL import Image
from matplotlib import pyplot as plt
import cv2 as cv
import time
from concurrent.futures import ThreadPoolExecutor
from paddlers.tasks.utils.visualize import visualize_detection
import sys

def INFO(info):
    print('FROM PYTHON: {}'.format(info))

def de_change(connectionSocket,root,base_dir,image_1,image_2,user):

    pre_dir = 'product/DeChange/Pre'
    pre_name = os.path.join(
            pre_dir, user+str(int(time.time()))+'.png')
    absolute_pre_name = os.path.join(base_dir, pre_name)

    INFO(image_1+' '+image_2+' '+absolute_pre_name)

    predictor = Predictor(root+"/target/classes/static/python/1024x1024",
                                  use_gpu=True)
    res = predictor.predict((image_1, image_2))
    cm_1024x1024 = res['label_map']
    cv.imwrite(absolute_pre_name, (cm_1024x1024*255).astype('uint8'))

    INFO(image_1+' '+image_2+' '+pre_name)

    connectionSocket.sendall(('/'+pre_name+'\r\n').encode('UTF-8'))

def get_target(connectionSocket,root,base_dir,image_1,user):
    pre_dir = 'product/GetTarget/Pre'
    pre_name = os.path.join(
            pre_dir, user+str(int(time.time()))+'.png')
    absolute_pre_name = os.path.join(base_dir, pre_name)

    INFO(image_1+' '+absolute_pre_name)

    predictor = Predictor(root+"/target/classes/static/python/1500x1500",
                                  use_gpu=True)
    res = predictor.predict((image_1))
    cm_1500x1500 = res['label_map']
    cv.imwrite(absolute_pre_name, (cm_1500x1500*255).astype('uint8'))

    INFO(pre_name)

    connectionSocket.sendall(('/'+pre_name+'\r\n').encode('UTF-8'))

def de_target(connectionSocket,root,base_dir,image_1,user,classes):
    pre_dir = 'product/DeTarget/Pre'
    pre_name = os.path.join(
            pre_dir, user+str(int(time.time()))+'.png')
    absolute_pre_name = os.path.join(base_dir, pre_name)
    model_path=''

    INFO(image_1+' '+absolute_pre_name)

    if classes.strip()=='playground':
        model_path='/target/classes/static/python/608x608_playground'
    elif classes.strip()=='aircraft':
        model_path='/target/classes/static/python/608x608_aircraft'
    elif classes.strip()=='overpass':
        model_path='/target/classes/static/python/608x608_overpass'
    else:
        model_path='/target/classes/static/python/608x608_oiltank'

    predictor = Predictor(root+model_path,use_gpu=True)
    res = predictor.predict((image_1))

    img=cv.imread(image_1)
    vis=img
    vis = visualize_detection(
        np.array(vis), res, 
        color=np.asarray([[0,255,0]], dtype=np.uint8), 
        threshold=0.2, save_dir=None
     )

    cv.imwrite(absolute_pre_name, vis)

    INFO(pre_name)

    connectionSocket.sendall(('/'+pre_name+'\r\n').encode('UTF-8'))

def class_geo(connectionSocket,root,base_dir,image_1,user):
    pre_dir = 'product/ClassGeo/Pre'
    pre_name = os.path.join(
            pre_dir, user+str(int(time.time()))+'.png')
    absolute_pre_name = os.path.join(base_dir, pre_name)
    model_path=''

    INFO(image_1+' '+absolute_pre_name)

    model_path='/target/classes/static/python/256x256'

    predictor = Predictor(root+model_path,use_gpu=True)
    res = predictor.predict((image_1))

    lut = np.zeros((256,3), dtype=np.uint8)
    lut[0] = [255, 0, 0]
    lut[1] = [30, 255, 142]
    lut[2] = [60, 0, 255]
    lut[3] = [255, 222, 0]
    lut[4] = [0, 0, 0]

    res = predictor.predict((image_1))

    cm_256x256 = res['label_map']
    cm_256x256=(cm_256x256*255).astype('uint8')
    cm_256x256=lut[cm_256x256]
    cv.imwrite(absolute_pre_name, cm_256x256)

    INFO(pre_name)

    connectionSocket.sendall(('/'+pre_name+'\r\n').encode('UTF-8'))

if __name__ == '__main__':

    with ThreadPoolExecutor(max_workers=10) as pool:

        serverSocket = socket(AF_INET, SOCK_STREAM)
        serverSocket.bind(('', 9292))
        serverSocket.listen(1)

        # root = '/media/vinay/Data/myFile/sharing_files/from_ubuntu/firstSpringBoot'
        root=sys.argv[1]
        base_dir = root+'/target/classes/static'

        INFO(root)

        while True:
            try:
                INFO('ready to serve')
                connectionSocket, addr = serverSocket.accept()
                INFO(addr)
                message = connectionSocket.recv(1024)
                data = message.decode()
                INFO(data)

                data_split = data.split('&')
                product=data_split[-1].strip()
                if product=='DeChange':
                    image_1 = os.path.join(
                            base_dir, data_split[0][1:len(data_split[0])])
                    image_2 = os.path.join(
                            base_dir, data_split[1][1:len(data_split[1])])
                    user=data_split[2]
                    pool.submit(de_change,connectionSocket,root,base_dir,image_1,image_2,user)
                    continue

                if product=='GetTarget':
                     image_1 = os.path.join(
                            base_dir, data_split[0][1:len(data_split[0])])
                     user=data_split[1]
                     pool.submit(get_target,connectionSocket,root,base_dir,image_1,user)
                     continue

                if product.find('DeTarget')!=-1:
                     image_1 = os.path.join(
                            base_dir, data_split[0][1:len(data_split[0])])
                     user=data_split[1]
                     classes=product.split(':')[1]
                     pool.submit(de_target,connectionSocket,root,base_dir,image_1,user,classes)
                     continue

                if product.find('ClassGeo')!=-1:
                     image_1 = os.path.join(
                            base_dir, data_split[0][1:len(data_split[0])])
                     user=data_split[1]
                     pool.submit(class_geo,connectionSocket,root,base_dir,image_1,user)
                     continue
            except Exception as e:
                INFO(e)
                continue
