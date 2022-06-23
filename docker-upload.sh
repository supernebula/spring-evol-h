cp -r evol-shenyu-gateway/src/docker dockerout/evol-shenyu-gateway
cp -r evol-server/evol-auth-server/src/docker dockerout/evol-auth-server
cp -r evol-server/evol-business-server/src/docker dockerout/evol-business-server
cp -r evol-server/evol-order-server/src/docker dockerout/evol-order-server
cp -r evol-server/evol-user-server/src/docker dockerout/evol-user-server
cp docker-compose-server.yml dockerout/docker-compose.yml

scp -r /Users/evol/source/github/spring-evol-h/dockerout root@10.211.55.11:/home/admin/evol-service