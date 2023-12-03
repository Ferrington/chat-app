import axios from 'axios';

export default {
  getAll() {
    return axios.get('/channels');
  },
  getFirst() {
    return axios.get('/channels/first');
  },
};
