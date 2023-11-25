FROM public-docker-virtual.artylab.expedia.biz/marketing-apps-base-java-17:1.0.1


COPY build/certs/rds-ca-2019-root.cer ./
RUN  keytool -importcert -trustcacerts -file ./rds-ca-2019-root.cer -alias rds19 -keystore rds-ca-certs -storepass changeit