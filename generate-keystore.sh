#!/bin/sh
# Generate self-signed keystore for HTTPS (run once, then enable SSL in application.properties)
KEYSTORE="src/main/resources/keystore.p12"
ALIAS="workspaceservice"
PASSWORD="changeit"
VALIDITY=3650

if [ -f "$KEYSTORE" ]; then
  echo "Keystore already exists: $KEYSTORE"
  echo "Delete it first if you want to regenerate."
  exit 0
fi

keytool -genkeypair -alias "$ALIAS" -storetype PKCS12 -keyalg RSA -keysize 2048 \
  -keystore "$KEYSTORE" -storepass "$PASSWORD" -validity "$VALIDITY" \
  -dname "CN=localhost, OU=WorkspaceService, O=CapitalMarkets, L=City, ST=State, C=US"

if [ $? -eq 0 ]; then
  echo ""
  echo "Keystore created: $KEYSTORE"
  echo "Uncomment the server.ssl.* lines in src/main/resources/application.properties"
  echo "Then run the app - it will listen on https://localhost:8443"
else
  echo "keytool failed. Ensure JAVA_HOME is set and keytool is on PATH."
  exit 1
fi
