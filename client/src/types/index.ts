import { z } from 'zod';

export const userSchema = z.object({
  id: z.number(),
  username: z.string(),
  accessToken: z.string(),
  tokenType: z.string(),
  roles: z.array(z.string()),
});

export type User = z.infer<typeof userSchema>;
