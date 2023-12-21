import './assets/main.css';

import { createPinia } from 'pinia';
import { createApp } from 'vue';

import axios from 'axios';
import App from './App.vue';
import router from './router';

//font awesome imports.  for more information visit the website in the chat-app discord channel. The beginning of this process will require you to create a .npmrc file
// which will ensure that your system resolves the "login" with a free license and does not resolve with needing a password. you will also need to run a few npm commands as well

//The first import creates the library where we store the font awesome icons we would like
//to use for the project. this will be an ever growing list if we continue to use font awesome 
//as its more space and time efficient than installing and loading the WHOLE thing everytime. 
//we wont need that many icons...i hope lol. 
import { library } from '@fortawesome/fontawesome-svg-core';
//The second line is adding a component into vue such that we can call
//the icons in any .vue file the syntax when calling looks like this: <font-awesome-icon :icon="['fas', 'paper-plane']" />
//there are multiple different syntax's you can use but i feel as though this one is the most specific which will lead to less
//ambiguity and mistakes in its use. Feel free to tell me im wrong though!  I would love to know if theres a better way than what i cobbled together in like 15 mins:)
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome';
//The third line is addding the specific icon we're looking to use in this case its a paper plane but it will eventually be other things.
import { faPaperPlane } from '@fortawesome/free-solid-svg-icons';
import { faPenToSquare } from '@fortawesome/free-solid-svg-icons';
import { faTrashCan } from '@fortawesome/free-solid-svg-icons';

//this line adds the imported icons into our library. One thing to keep in mind is that you dont need several lines for multiple adds. should be able to do it as csv's 
//within the parenthetical statement.
library.add(faPaperPlane, faPenToSquare, faTrashCan);


axios.defaults.baseURL = import.meta.env.VITE_REMOTE_API + '/api';

const app = createApp(App);

app.use(createPinia());
app.use(router);
app.component('font-awesome-icon', FontAwesomeIcon);
app.mount('#app');

