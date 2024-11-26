Docker command to:
    start mongo db image
    attach a volume to it d:/data
    attach it to hosts port 27017
    pass username and password

docker run --name mongodb -d -p 27017:27017 -v d:/data:/data/db -e MONGO_INITDB_ROOT_USERNAME=root -e MONGO_INITDB_ROOT_PASSWORD=root mongodb/mongodb-community-server