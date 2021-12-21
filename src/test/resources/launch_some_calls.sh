!/bin/bash

for i in `seq 1 5`
do
  curl http://localhost:8081/fizzbuzz/2/5/10/fizz/buzz
done

for i in `seq 1 10`
do
  curl http://localhost:8081/fizzbuzz/3/4/12/toto/tata
done

for i in `seq 1 3`
do
  curl http://localhost:8081/fizzbuzz/20/15/100/boo/baa
done

#wrong request

for i in `seq 1 2`
do
  curl http://localhost:8081/fizzbuzz/0/15/100/boo/baa
done
for i in `seq 1 2`
do
  curl http://localhost:8081/fizzbuzz/20/0/100/boo/baa
done
for i in `seq 1 2`
do
  curl http://localhost:8081/fizzbuzz/20/10/-100/boo/baa
done