**Project Name - Docker Compose Setup**

## Description

This project demonstrates how to run a full-stack application with Docker Compose. It consists of a backend (Spring-boot) and a frontend (Angular) application. Docker Compose is used to orchestrate and manage both services.

## Prerequisites

Before you begin, ensure you have the following installed:

- Docker: [Docker Installation Guide](https://docs.docker.com/get-docker/)
- Docker Compose: [Docker Compose Installation Guide](https://docs.docker.com/compose/install/)

## Getting Started

1. Clone the repository to your local machine:

   ```bash
   git clone git@github.com:Rodielm/tool_ecommerce_test.git
   ```

2. Navigate to the project's root directory:

   ```bash
   cd "PROJECT_DIRECTORY"
   ```

3. rename `.env.example` to `.env` file in the project's root directory and set the necessary environment variables. Here's an example for reference:

   ```env
      DB_HOST=<DB_HOST>
      DB_USER=<DB_USER>
      DB_PASSWORD=<DB_PASS>
      DB_ROOT_PASSWORD=<DB_ROOT_PASS>
   ```

4. Build and start the Docker containers using Docker Compose:

   ```bash
   docker-compose up --build
   ```

   - This command will build and start the backend and frontend services according to the configuration in the `docker-compose.yml` file.

5. Once the containers are running, you can access the following services in your web browser:

   - Backend API: `http://localhost:8081`
   - Frontend Application: `http://localhost:4200`

## Additional Configuration

### Backend Configuration

- Update the backend environment variables in the `.env` file to match your specific configuration.

### Frontend Configuration

- If you have specific frontend build or environment requirements, update the frontend service in the `docker-compose.yml` file.

## Stopping the Application

To stop the Docker containers, open a new terminal window and navigate to the project's root directory. Run the following command:

```bash
docker-compose down
```

This will stop and remove the containers.

## Cleanup

If you wish to remove all containers, networks, and volumes associated with this project, you can run:

```bash
docker-compose down -v
```

## Acknowledgments

- Mention any libraries, tools, or people whose work you've used or are grateful for.