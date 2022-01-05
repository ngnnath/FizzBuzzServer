!/bin/bash

for i in `seq 1 5`
do
  curl http://localhost:8081/fizzbuzz/int1/2/int2/5/limit/10/str1/fizz/str2/buzz
done

for i in `seq 1 10`
do
  curl http://localhost:8081/fizzbuzz/int1/3/int2/4/limit/12/str1/toto/str2/tata
done

for i in `seq 1 3`
do
  curl http://localhost:8081/fizzbuzz/int1/20/int2/15/limit/100/str1/boo/str2/baa
done

#wrong request

for i in `seq 1 2`
do
  curl http://localhost:8081/fizzbuzz/int1/0/int2/15/limit/100/str1/boo/str2/baa
done
for i in `seq 1 2`
do
  curl http://localhost:8081/fizzbuzz/int1/20/int2/0/limit/100/str1/boo/str2/baa
done
for i in `seq 1 2`
do
  curl http://localhost:8081/fizzbuzz/int1/20/int2/10/limit/-100/str1/boo/str2/baa
done