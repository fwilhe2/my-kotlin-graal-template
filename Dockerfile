FROM gcr.io/distroless/base

COPY app/build/graal/hello-world /hello

CMD [ "/hello" ]