import requests;
import csv;
import pymysql;
csvfile=open("theatreids.csv","r")
listOfRows=csv.reader(csvfile, delimiter=' ')
writeFile=open("moviedata.txt","w");
for row in listOfRows:
    url="http://www.omdbapi.com/?i="+str(row[0])+"&apikey=53cba08"
    response = requests.get(url)
    responseJson = response.text
    writeFile.write(responseJson+" , ")
writeFile.close()