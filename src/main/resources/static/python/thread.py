from concurrent.futures import ThreadPoolExecutor
import os

def hello(*string):
    print(string)

# with ThreadPoolExecutor(max_workers=10) as pool:
#     for i in range(5):
#         pool.submit(hello,'222222222222222222222222222222222222222222222222222','3333','55555555')
#         print('ll')
#
def killport(port):
	command='''kill -9 $(netstat -nlp | grep :'''+str(port)+''' | awk '{print $7}' | awk -F"/" '{ print $1 }')'''
	os.system(command) 

killport(9292)
