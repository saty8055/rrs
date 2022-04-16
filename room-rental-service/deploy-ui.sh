cd /users/shadow/desktop/sandbox/Room-Rs/room-rental-service/src/main/room-rental-admin
echo "================================================================================================================================="
echo "Checking port : 6060 to make it available for use..."
lsof -i :6060
echo "Clean...."
kill $(lsof -t -i:6060)
echo "================================================================================================================================="

ng serve --port=6060