import axios from 'axios';

export default {
  register(user: any) {
    return axios.post('/auth/register', user);
  },
  login() {
    const user = {
      username: 'ferrington',
      password: 'sausages',
    };
    return axios.post('/auth/login', user);
  },
};
