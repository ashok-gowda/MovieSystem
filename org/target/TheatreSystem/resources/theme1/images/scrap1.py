#import csv
import requests;
import re
import pymysql;
import json;
import urllib
with open('moviedata.json',"rb") as data_file:
    responseJsonList = json.load(data_file)
db=pymysql.connect(host="127.0.0.1", user="root", password="root")
cursor = db.cursor()
cursor.execute("use theatre")
try:
    selectQuery="Select id,poster from movie where id>204 and poster NOT LIKE '%N/A%'"
    cursor.execute(selectQuery)
    rows=cursor.fetchall()
    for row in rows:
        filename=str(row[0])+".jpg"
        urllib.request.urlretrieve(row[1],filename);
except Exception as e:
    print(e)
    db.rollback()
    db.close()

