#Get all names and url from bookmark, write to txt file
#import requests as rq
from bs4 import BeautifulSoup

file ='./bjk.html' #Bookmark location
htmlhandle = open(file, 'r', encoding='utf-8').read()
text = open("op.txt", "w", encoding="utf-8")

soup = BeautifulSoup(htmlhandle, 'html.parser')

for link in soup.find_all('a'):
    k = str(link.contents[0]) if len(link.contents) != 0 else "" 
    text.writelines(k + "\n")
    text.writelines(str(link.get('href')) + "\n")

text.close()