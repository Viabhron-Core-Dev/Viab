#!/usr/bin/env sh

# Minimal gradlew script
APP_NAME="Gradle"
APP_BASE_NAME=`basename "$0"`

# Attempt to find java
if [ -n "$JAVA_HOME" ] ; then
    JAVACMD="$JAVA_HOME/bin/java"
else
    JAVACMD="java"
fi

if [ ! -x "$JAVACMD" ] ; then
    die "ERROR: JAVA_HOME is set to an invalid directory: $JAVA_HOME
Please set the JAVA_HOME variable in your environment to match the
location of your Java installation."
fi

# Attempt to run the wrapper jar
if [ -f "gradle/wrapper/gradle-wrapper.jar" ]; then
    exec "$JAVACMD" -jar "gradle/wrapper/gradle-wrapper.jar" "$@"
else
    # Fallback to system gradle if available
    if command -v gradle >/dev/null 2>&1; then
        exec gradle "$@"
    else
        echo "ERROR: gradle/wrapper/gradle-wrapper.jar not found and 'gradle' command not found in PATH."
        exit 1
    fi
fi
