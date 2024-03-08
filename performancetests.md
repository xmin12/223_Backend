# Performance Tests

As stated in the documentation, we are testing the performance of our application by sending multiple requests simultaneously. Another performance testing method we used was testing the database performance when performing multiple transactions at once.

## PGBench

### Settings
For this test, we used the following settings:
- Number of clients: 10
- Number of threads: 10
- Number of transactions per client: 1,000,000

### Test

This is how our stress-test with PGBench looks like:

![PGBench](/images/pgbench-stresstest.png)

### Conclusion
Our conclusion from this test is that our PostgreSQL database is very stable as it can handle one million transactions at the same time.

## JMeter
This is how our performance test with JMeter looks like:

![JMeter](/images/ten-thousand.JPG)

### Test 1

#### Settings

For this test, we used the following settings:
- Number of Threads: 10,000
- Ramp-up period: 1
- Loop count: 1

This is how our performance-test 1 with JMeter looks like:

![Test 1 Graph](/images/graph.JPG)

(This is a graph displaying the number of HTTP requests that are executed over time)

### Test 2

#### Settings

For this test, we used the following settings:
- Number of Threads: 1,000
- Ramp-up period: 1
- Loop count: 1

This is how our performance-test 2 with JMeter looks like:

![Test 2 Graph](/images/graph-two.JPG)

(This is a graph displaying the number of HTTP requests that are executed over time)

### Conclusion
Our conclusion from this test is that the amount of 10,000 requests at the same time is definitely too much for our application, as we can see in the [Test 1](#test-1) graph. However, this scenario is very unlikely to happen. What's more likely to happen is an amount of 1,000 requests at a time, which, as you can see in [Test 2](#test-2) graph, is handled easily by our application.
