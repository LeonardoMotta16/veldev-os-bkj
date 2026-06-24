#!/bin/bash
export DB_URL="jdbc:h2:mem:veldevdb"
export DB_USERNAME="sa"
export DB_PASSWORD=""
export JWT_SECRET="troque-por-uma-chave-segura"
export JWT_EXPIRATION_MS="86400000"

./mvnw spring-boot:run
