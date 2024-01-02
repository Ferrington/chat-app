import { z } from 'zod';

export const userSchema = z.object({
  id: z.number(),
  username: z.string(),
  accessToken: z.string(),
  tokenType: z.string(),
  roles: z.array(z.string()),
});

export type User = z.infer<typeof userSchema>;

export type UserDTO = {
  username: string;
  password: string;
};
export const messageSchema = z.object({
  id: z.number(),
  userId: z.number(),
  username: z.string(),
  channelId: z.number(),
  content: z.string(),
  created: z.string().pipe(z.coerce.date()),
  updated: z.string().pipe(z.coerce.date()),
});

export type Message = z.infer<typeof messageSchema>;
