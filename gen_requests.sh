#!/bin/bash

url=localhost:8080/metrics

echo 'Generating requests...'
body='{"content":"created at '"`date +%H:%M:%S:%ND`"'"}'
echo ${body}
echo '{"content":"created"}'


for i in {1..20}
    do
        echo ${i}
#        curl -H "Content-Type: application/json" -d '{"content":"created at '"`date +%H:%M:%S:%ND`"'", "chunkSequence": '${i}'}' localhost:8080/metrics &
#        curl -H "Content-Type: application/json" -d '{"content":"created at '"`date +%H:%M:%S:%ND`"'", "chunkSequence": '${i}'}' localhost:8080/metrics &
#        curl -H "Content-Type: application/json" -d '{"content":"created at '"`date +%H:%M:%S:%ND`"'", "chunkSequence": '${i}'}' localhost:8080/metrics &
#        curl -H "Content-Type: application/json" -d '{"content":"created at '"`date +%H:%M:%S:%ND`"'", "chunkSequence": '${i}'}' localhost:8080/metrics &
        curl -H "Content-Type: application/json" -d '{"content":"created at '"`date +%H:%M:%S:%ND`"'", "chunkSequence": '${i}'}' localhost:8080/metrics &
        curl -H "Content-Type: application/json" -d '{"content":"created at '"`date +%H:%M:%S:%ND`"'", "chunkSequence": '${i}'}' localhost:8080/metrics &
        curl -H "Content-Type: application/json" -d '{"content":"created at '"`date +%H:%M:%S:%ND`"'", "chunkSequence": '${i}'}' localhost:8080/metrics &
        curl -H "Content-Type: application/json" -d '{"content":"created at '"`date +%H:%M:%S:%ND`"'", "chunkSequence": '${i}'}' localhost:8080/metrics
        echo
        sleep 3s
    done


echo ''