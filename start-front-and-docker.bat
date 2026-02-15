@echo off
setlocal

call "D:\Local\Dev\java\giustore\back\docker\start-docker.bat"
if errorlevel 1 (
  echo Failed to start Docker services.
  exit /b 1
)

cd /d "D:\Local\Dev\java\giustore\front"
call npm run dev

endlocal
