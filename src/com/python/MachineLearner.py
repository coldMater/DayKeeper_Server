
# coding: utf-8

# In[1]:

import urllib.request as req
import os.path, random
import json


# In[2]:

import json
import csv
import pandas as pd

# In[3]:
import sys
path = sys.argv[1]
date = sys.argv[2]
precision =0;
report  ="";


# In[4]:

with open(path) as data_file:    
    list1 = json.load(data_file)
# In[5]:

name = ['action','dayOfWeek','hour','isWeekend','month','weekOfMonth']
actiontList = []
dayOfWeekList = []
hourList = []
isWeekendeList = []
monthList = []
weekOfMonthList = []


# In[6]:

for item in list1:
    actiontList.append(item["action"])
    dayOfWeekList.append(item["dayOfWeek"])
    hourList.append(item["hour"])
    isWeekendeList.append(item["isWeekend"])
    monthList.append(item["month"])
    weekOfMonthList.append(item["weekOfMonth"])


# In[7]:

from pandas import Series, DataFrame


# In[8]:

data={name[0]:actiontList,
     name[1]:dayOfWeekList,
     name[2]:hourList,
     name[3]:isWeekendeList,
     name[4]:monthList,
     name[5]:weekOfMonthList}     
#actiontList

#dayOfWeekList+dayOfWeekList
index=0
resultlist = []
for i in enumerate(dayOfWeekList):
    resultlist.append([dayOfWeekList[i[0]],hourList[i[0]],isWeekendeList[i[0]],monthList[i[0]],weekOfMonthList[i[0]]])


# In[9]:

from sklearn.ensemble import RandomForestClassifier
from sklearn.datasets import make_moons
from sklearn.model_selection import train_test_split
from sklearn import metrics


# In[10]:

X_train, X_test, y_train, y_test = train_test_split(resultlist, actiontList) # 100 ,20 ,18


# In[11]:

forest = RandomForestClassifier(n_estimators = 29, random_state=22)
forest.fit(X_train, y_train)
predict = forest.predict(X_test)
ac_score = metrics.accuracy_score(y_test, predict)
cl_report = metrics.classification_report(y_test, predict)
precision = ac_score
report = cl_report


# In[12]:

import datetime
testList=[]

for i in ['00','01','02','03','04','05','06','07','08','09','10','11','12','13','14','15','16','17','18','19','20','21','22','23']:
    myDatetimeStr = date+' '+i+':00:00'
    myDatetime = datetime.datetime.strptime(myDatetimeStr, '%Y-%m-%d %H:%M:%S')

    hour = myDatetime.hour

    dayOfWeek = myDatetime.weekday()

    if myDatetime.weekday()<5:
        isWeekend = 0
    else:
        isWeekend = 1
    
    weekOfMonth = int(myDatetime.day/7)+1
    
    month = (myDatetime.month)
    #'dayOfWeek','hour','isWeekend','month','weekOfMonth'
    

    testList.append([dayOfWeek,hour,isWeekend,month,weekOfMonth])


# In[14]:

resultList = forest.predict_proba(testList)


# In[117]:

reportString ="["
for i in enumerate(resultList):
    tempList = i[1]
    max = 0;
    index = 0;
    jsonObj ="";
    for j in enumerate(tempList):
        jsonObj ="";
        if j[1]>max:
            max=j[1]
            index = j[0]+1
    jsonObj += "{"
    jsonObj += "\'"
    jsonObj += "catID"
    jsonObj += "\'"
    jsonObj += ":"
    jsonObj += "\'"
    jsonObj += str(index)
    jsonObj += "\'"
    jsonObj += ","
    jsonObj += "\'"
    jsonObj += "carProb"
    jsonObj += "\'"
    jsonObj += ":"
    jsonObj += "\'"
    jsonObj += str(max)
    jsonObj += "\'"
    jsonObj += "}"
    reportString += jsonObj
    
    if i[0]<23:
        reportString += ","
    else :
        reportString +="]"


# In[118]:

print(reportString)


