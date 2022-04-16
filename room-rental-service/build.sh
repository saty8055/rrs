echo "================================================================================================================================="

echo "Building UI Assets...."
cd /users/shadow/desktop/sandbox/Room-Rs/room-rental-service/src/main/room-rental-admin

ng build

echo "--------------------------------------------------------------------------------------------------------------------------------"

echo "Moving to Back-end library..."

cd dist/room-rental-admin

mv index.html ./../../../resources/templates/index.html

cd ../..

cp -R ./dist/room-rental-admin/ ./../resources/static

echo "Cleaning unused files..."

rm -rf dist