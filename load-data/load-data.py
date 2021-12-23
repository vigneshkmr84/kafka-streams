
import json
import requests


#file=open('./movies_data.json')
file=open('./striped_data.json')

data=json.load(file)


url="http://localhost:9090/insert"
headers={'Content-Type': 'application/json'}

for d in data['movies']:
    payload=json.dumps(d)
    res = requests.request('POST', url, headers=headers, data=payload)
    print(res.text)

file.close()


