# junit5-log-capture-demo

Verifying that the correct diagnostic information is logged is often neglected in unit tests. It is important to verify that the correct messages are logged. The last thing developers would want is discover that no information or wrong information has been logged just when they need it the most (e.g. during a production issue).

In JUnit4 you can capture log messages using rules (see https://gist.github.com/geowarin/3685379c1ae5f100a02c)

This demo project shows how to capture log messages with JUnit5.
