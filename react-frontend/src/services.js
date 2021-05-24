import axios from 'axios';

export const getAllAvatars = async (opts) => {
  if (!opts) return null;
  const response = await axios.get(`/api/users/${opts.userId}/avatars`);
  return response.data;
}

export const getAvatar = async (opts) => {
  if (!opts.userId) return {};
  const response = await axios.get(`/api/users/${opts.userId}/avatars/${opts.avatarId}`);
  return response.data;
}

export const deleteAvatar = async (opts) => {
  await axios.delete(`/api/users/${opts.userId}/avatars/${opts.avatarId}`);
  console.log(`Avatar ${opts.avatarId} deleted`);
};

export const updateAvatar = async (opts) => {
  const response = await axios.put(`/api/users/${opts.userId}/avatars/${opts.avatarId}`, opts.data);
  return response.data;
}

export const createAvatar = async (opts) => {
  const response = await axios.post(`/api/users/${opts.userId}/avatars`, opts.data);
  return response.data;
};

export const getUser = async (opts) => {
  if (!opts) return null;
  const response = await axios.get(`/api/users/${opts.userId}`);
  return response.data;
};
