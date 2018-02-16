#!/usr/bin/env bash
echo curl -H "Content-Type: application/json" -X POST -d '{"name":"foo","description":"bar","status":"NEW","partition":"ONE", "hours":2}' http://localhost:8090/task;
curl -H "Content-Type: application/json" -X POST -d '{"name":"foo","description":"bar","status":"NEW","partition":"ONE", "hours":2}' http://localhost:8090/task;
echo curl -H "Content-Type: application/json" -X POST -d '{"name":"foo","description":"bar","status":"NEW","partition":"TWO", "relatedTaskId":"", "hours":2}' http://localhost:8090/task;
curl -H "Content-Type: application/json" -X POST -d '{"name":"foo","description":"bar","status":"NEW","partition":"TWO", "relatedTaskId":"", "hours":2}' http://localhost:8090/task;
echo curl -H "Content-Type: application/json" -X POST -d '{"name":"foo","description":"bar","status":"PROCESSING","partition":"ONE", "relatedTaskId":"0",  "hours": 2}' http://localhost:8090/task;
curl -H "Content-Type: application/json" -X POST -d '{"name":"foo","description":"bar","status":"PROCESSING","partition":"ONE", "relatedTaskId":"0",  "hours": 2}' http://localhost:8090/task;
echo curl -H "Content-Type: application/json" -X POST -d '{"name":"foo","description":"bar","status":"PROCESSING","partition":"TWO", "relatedTaskId":"1",  "hours": 2}' http://localhost:8090/task;
curl -H "Content-Type: application/json" -X POST -d '{"name":"foo","description":"bar","status":"PROCESSING","partition":"TWO", "relatedTaskId":"1",  "hours": 2}' http://localhost:8090/task;
echo curl -H "Content-Type: application/json" -X POST -d '{"name":"foo","description":"bar","status":"DONE","partition":"ONE", "relatedTaskId":"0",  "hours": 2}' http://localhost:8090/task;
curl -H "Content-Type: application/json" -X POST -d '{"name":"foo","description":"bar","status":"DONE","partition":"ONE", "relatedTaskId":"0",  "hours": 2}' http://localhost:8090/task;
echo curl -H "Content-Type: application/json" -X POST -d '{"name":"foo","description":"bar","status":"REJECTED","partition":"TWO", "relatedTaskId":"1",  "hours": 2}' http://localhost:8090/task;
curl -H "Content-Type: application/json" -X POST -d '{"name":"foo","description":"bar","status":"REJECTED","partition":"TWO", "relatedTaskId":"1",  "hours": 2}' http://localhost:8090/task;
