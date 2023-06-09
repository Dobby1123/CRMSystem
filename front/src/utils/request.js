import axios from 'axios';

const service = axios.create({
    timeout: 5000
});

service.interceptors.request.use(
    config => {
        return config;
    },
    error => {
        console.log(error);
        return Promise.reject();
    }
);

service.interceptors.response.use(
    response => {
        if (response.status === 200) {
            return response.data;
        } else {
            Promise.reject();//返回一个带有拒绝原因的Promise对象。
        }
    },
    error => {
        console.log(error);
        return Promise.reject();
    }
);

export default service;
