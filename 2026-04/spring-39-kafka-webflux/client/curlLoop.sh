date
for run in {1..100000}; do
  curl -s "http://localhost:8082/data-mono/$run" > /dev/null
done
date