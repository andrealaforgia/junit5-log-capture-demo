# junit5-log-capture-demo
Demo project showing how to capture log messages with JUnit5 for test purposes

Verifying that the correct diagnostic information is logged is often neglected in unit tests. It is important to verify that the correct messages are logged. The last thing developers would want is to discover that no information or wrong information has been logged just when they need it the most (e.g. during a production issue).

In JUnit4 you can capture log messages using rules (see https://gist.github.com/geowarin/3685379c1ae5f100a02c)

This sample project shows how to use extensions in JUnit5 instead.
