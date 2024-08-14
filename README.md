# Java Jersey API with Hibernate 

# Useful Sources
https://www.baeldung.com/jersey-test

Pass Key: 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 


# Docket Tutorial 
### Introduction to Docker

Docker is a platform designed to help developers build, deploy, and run applications inside containers. Containers are lightweight, portable, and self-sufficient environments that contain everything needed to run a piece of software, including the code, runtime, system tools, libraries, and settings.

### Key Terms

1. **Container**: A standalone, executable package that includes everything needed to run a piece of software.
2. **Image**: A read-only template used to create containers. Images contain the application code, libraries, tools, dependencies, and other files.
3. **Dockerfile**: A text file that contains instructions on how to build a Docker image.
4. **Docker Hub**: A cloud-based repository where Docker users can store and share container images.
5. **Docker Compose**: A tool for defining and running multi-container Docker applications.

### Installing Docker on Mac

1. **Download Docker Desktop**:
   - Visit the [Docker Desktop for Mac](https://www.docker.com/products/docker-desktop) download page.
   - Download and install the application by following the on-screen instructions.

2. **Install Docker Desktop**:
   - Open the downloaded Docker.dmg file and drag the Docker icon to the Applications folder.
   - Launch Docker from the Applications folder.
   - Follow the setup instructions. You may need to provide your system password to complete the installation.

3. **Verify Installation**:
   - Open a terminal and run the following command to verify that Docker is installed correctly:
     ```bash
     docker --version
     ```
   - You should see the version of Docker installed.

### Basic Docker Commands

1. **Pulling an Image**:
   - To download an image from Docker Hub, use the `docker pull` command:
     ```bash
     docker pull hello-world
     ```

2. **Running a Container**:
   - To run a container using the downloaded image, use the `docker run` command:
     ```bash
     docker run hello-world
     ```
   - This command will run the `hello-world` image in a container and print a welcome message.

3. **Listing Containers**:
   - To list all running containers, use the `docker ps` command:
     ```bash
     docker ps
     ```
   - To list all containers (including stopped ones), use the `docker ps -a` command:
     ```bash
     docker ps -a
     ```

4. **Stopping a Container**:
   - To stop a running container, use the `docker stop` command followed by the container ID or name:
     ```bash
     docker stop <container_id>
     ```

5. **Removing a Container**:
   - To remove a container, use the `docker rm` command followed by the container ID or name:
     ```bash
     docker rm <container_id>
     ```

6. **Removing an Image**:
   - To remove an image, use the `docker rmi` command followed by the image ID or name:
     ```bash
     docker rmi <image_id>
     ```

### Creating a Dockerfile

A Dockerfile is a script containing instructions on how to build a Docker image. Here is a simple example:

```dockerfile
# Use an official Python runtime as a parent image
FROM python:3.8-slim

# Set the working directory in the container
WORKDIR /app

# Copy the current directory contents into the container at /app
COPY . /app

# Install any needed packages specified in requirements.txt
RUN pip install --no-cache-dir -r requirements.txt

# Make port 80 available to the world outside this container
EXPOSE 80

# Define environment variable
ENV NAME World

# Run app.py when the container launches
CMD ["python", "app.py"]
```

### Building and Running an Image

1. **Build the Image**:
   - Navigate to the directory containing your Dockerfile and run:
     ```bash
     docker build -t my-python-app .
     ```

2. **Run the Container**:
   - To run the container from the image you just built, use:
     ```bash
     docker run -p 4000:80 my-python-app
     ```
   - This maps port 4000 on your host to port 80 on the container.

### Docker Compose

Docker Compose is used to manage multi-container applications. Here’s a simple example using a `docker-compose.yml` file:

```yaml
version: '3'
services:
  web:
    image: my-python-app
    build: .
    ports:
      - "4000:80"
  redis:
    image: "redis:alpine"
```

To start the application, run:

```bash
docker-compose up
```

To stop the application, run:

```bash
docker-compose down
```

### Summary

Docker is a powerful tool for containerizing applications, making them easy to run, manage, and deploy. By understanding basic Docker concepts and commands, and by using tools like Docker Compose, you can efficiently manage and deploy your applications in any environment.
