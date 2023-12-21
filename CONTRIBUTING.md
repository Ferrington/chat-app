# Welcome to Chat App contributing guide

## Table Of Contents
[New Contributor Guide](#new-contributor-guide)

[Styling Rules](#styling-rules)


## New Contributor Guide

In order to run the project on your computer, you need to do the following:

Clone the repo onto your machine
```
git clone https://github.com/Ferrington/chat-app.git
```

Please note that this repo contains code for both the client and server. You should open the appropriate subfolders in your code editor of choice when working.

#### Setting up the server
It is assumed you have [PostgreSQL](https://www.postgresql.org/download/) installed on your machine. Create a new database for use with this project. Spring data jpa will manage creation and modification of tables. You may edit `chap-app/server/src/main/resources/data.sql` to populate the tables with test data.

If you have not previously used Lombok, your IDE should prompt you to install a Lombok plugin when you open the project. Accept the install and enable live processing when it prompts you to do so.

Create the file `chat-app/server/env.properties` and modify the below content with your postgres connection info.
```
DB_HOST=localhost
DB_PORT=5432
DB_NAME=<your postgres database name>
DB_USER=<your postgres username>
DB_PASSWORD=<your postgres password>
DB_DDL=create-drop
INIT_MODE=ALWAYS
JWT_SECRET=<a 64 character base64 encoded secret>
JWT_EXPIRATION_MS=600000
```

You should now be able to run the server.

#### Setting up the client

Create the file `chat-app/client/.env` with the following contents. This will set the base url for axios.
```
VITE_REMOTE_API=http://localhost:8080
```

Install the project dependencies
```
npm install
```

You can now run the client
```
npm run dev
```

Check the `package.json` for more useful commands.

Assuming you are using [VS Code](https://code.visualstudio.com/), you'll want to install the following extensions:
- [Vue Language Features (Volar)](vscode:extension/Vue.volar)
- [TypeScript Vue Plugin (Volar)](vscode:extension/Vue.vscode-typescript-vue-plugin)
- [ESLint](vscode:extension/dbaeumer.vscode-eslint)
- [Prettier](vscode:extension/esbenp.prettier-vscode) (I recommend setting this to [run on save](https://stackoverflow.com/a/75582834))

## Styling Rules

It is our goal to maintain a similar look and feel for all code across the project. This guide is focused on the front end (client) at this time because there it has so much room for variation.

The first line of style defense (and simplest to implement) is using Prettier to autoformat our code. For a painless experience, I recommend configuring prettier to [run on save](https://stackoverflow.com/a/75582834). If you have existing "unprettified" code, you can run `npm run format` to format the entire project.

We will follow the [Vue Style Guide](https://vuejs.org/style-guide/) with some additional opinionated rules.

#### Naming Conventions
Component names - Pascal case `<MyComponent />`

CSS Classes - Kebab case `<div class="my-class"></div>`

#### Composition API
The script portion of Vue SFC's should use the `setup` tag and specify the language as typescript. We will not use the options api object at all.
```
<script setup lang="ts">
</script>
```
