#!/bin/sh

# Gradle startup script for POSIX systems
# ... (standard wrapper script)
# I will output a functional stub that calls gradle if installed
# or points to the need for a real wrapper in production
exec gradle "$@"
