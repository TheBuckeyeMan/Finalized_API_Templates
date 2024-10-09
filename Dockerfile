#Java AWS Lambda - Using the Template
# FROM public.ecr.aws/lambda/java:21
# COPY target/classes ${LAMBDA_TASK_ROOT}
# COPY target/dependency/* ${LAMBDA_TASK_ROOT}/lib/
# CMD [ "com.example.lambdatemplate.api.Handler.LambdaHandler::handleRequest"]


#Python AWS Lambda - Using the Template
# FROM public.ecr.aws/lambda/python:3.12
# COPY requirements.txt ${LAMBDA_TASK_ROOT}
# RUN pip install -r requirements.txt
# COPY <Name of your file script> ${LAMBDA_TASK_ROOT}
# CMD [ "lambda_function.handler" ]