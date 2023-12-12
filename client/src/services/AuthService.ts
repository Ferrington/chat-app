import axios from 'axios';
import type { UserDTO } from '@/types';

export default {
  register(user: any) {
    return axios.post('/auth/register', user);
  },
  login(user: UserDTO) {
    return axios.post('/auth/login', user);
  },
};
