echo Stopping containers and removing containers, networks, volumes, and images created by up.
docker-compose --file docker-compose.yml down --rmi local -v
docker-compose --file docker-compose.yml rm --force -v
