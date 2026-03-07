@echo off

cd "E:\Java projects\PerlinNoiseTextureGenerator"
echo compiling...

@REM for /r src %%f in (*.java) do (javac -d bin -sourcepath src "%%f")
javac -d bin -sourcepath src src\Main.java

if %errorlevel% neq 0 (echo compilation failed. pause exit /b)
echo running...

java -cp bin Main