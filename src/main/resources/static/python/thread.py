from concurrent.futures import ThreadPoolExecutor

def hello(*string):
    print(string)

with ThreadPoolExecutor(max_workers=10) as pool:
    for i in range(5):
        pool.submit(hello,'222222222222222222222222222222222222222222222222222','3333','55555555')
        print('ll')


