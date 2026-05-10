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

# Attempt to run the wrapper jar
if [ -f "gradle/wrapper/gradle-wrapper.jar" ]; then
    # Verify if it's a valid jar before running
    if jar -tf gradle/wrapper/gradle-wrapper.jar > /dev/null 2>&1; then
        exec "$JAVACMD" -jar "gradle/wrapper/gradle-wrapper.jar" "$@"
    else
        echo "Warning: gradle-wrapper.jar is invalid or corrupt. Attempting recovery..."
        rm gradle/wrapper/gradle-wrapper.jar
    fi
fi

# Recovery: Try to use system gradle to regenerate or run
if command -v gradle >/dev/null 2>&1; then
    echo "Using system gradle..."
    exec gradle "$@"
fi

# Last resort: Try to download a known good version
WRAPPER_URL="https://services.gradle.org/distributions/gradle-8.2-bin.zip"
# Note: Services.gradle.org is the official source. 
# However, downloading the whole zip in a stub is slow.
# Let's try one more binary link that is often used for bootstrap
BOOTSTRAP_JAR="https://github.com/gradle/gradle/raw/v8.2.0/gradle/wrapper/gradle-wrapper.jar"

if command -v curl >/dev/null 2>&1; then
    echo "Attempting to download bootstrap jar..."
    mkdir -p gradle/wrapper
    curl -L "$BOOTSTRAP_JAR" -o gradle/wrapper/gradle-wrapper.jar
    if [ -f "gradle/wrapper/gradle-wrapper.jar" ]; then
         exec "$JAVACMD" -jar "gradle/wrapper/gradle-wrapper.jar" "$@"
    fi
fi

echo "ERROR: Unable to find or bootstrap Gradle."
exit 1
