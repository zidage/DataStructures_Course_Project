import request from '@/utils/request.js';

// 获取个人日记列表
export const getMyDiaries = (query) => {
  const validParams = {};
  for (const key in query) {
    if (query[key] !== null && query[key] !== undefined && query[key] !== '') {
      validParams[key] = query[key];
    }
  }

  return request.get('/diary/myDiaries', {
    params: validParams
  });
};

// 新增日记
export const createDiaryService = (diaryData) => {
  if (!diaryData.title || !diaryData.content || !diaryData.coverImg || !diaryData.state || !diaryData.planId || !diaryData.placeId) {
    throw new Error("Missing required diary fields");
  }

  return request.post('/diary/newDiary', diaryData);
};

// 获取单个日记信息
export const getDiaryByIdService = (diaryId) => {
  if (!diaryId) {
    throw new Error("Diary ID is required");
  }
  return request.get(`/diary/${diaryId}`);
};


// 日记评分
export const rateDiaryService = (diaryId, rating) => {
  if (!diaryId) {
    throw new Error("Diary ID is required");
  }

  const params = {};
  if (rating) {
    params.rating = rating;
  }

  return request.get('/diary/${diaryId}/rating', {
    params
  });
};

// 更新日记
export const updateDiaryService = (data) => {
  if (!data.id || !data.title || !data.content || !data.coverImg || !data.state || !data.planId || !data.placeId) {
    throw new Error("Missing required diary fields");
  }

  return request.post('/diary/update', data);
};

//获取社区日记
export const communityListService = (query) => {
  const validParams = {};
  for (const key in query) {
    if (query[key] !== null && query[key] !== undefined && query[key] !== '') {
      validParams[key] = query[key];
    }
  }

  return request.get('/diary/community', {
    params: validParams
  });
};
