@echo off
REM Check if the virtual machine is running
podman machine ls | findstr "Running" >nul
IF %ERRORLEVEL% NEQ 0 (
    echo Starting Podman machine...
    podman machine start
    timeout /t 5
)

REM Execute Docker Compose with Podman
podman-compose up -d