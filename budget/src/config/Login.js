import Swal from 'sweetalert2';
import axios_api from './Axios';

const JWT_EXPIRY_TIME = 24 * 3600 * 1000; // 만료 시간 (24시간 밀리 초로 표현)

export function onLogin() {
    // const accessToken = getCookie('accessToken');
    // if (accessToken !== undefined) {
    //     // accessToken header로 설정
    //     axios_api.defaults.headers.common['Authorization'] =
    //         `Bearer ${accessToken}`;
    // } else {
    //     // alert('로그인 후 접근 가능해요!');
    //     Swal.fire({
    //         icon: 'warning',
    //         text: '로그인 후 접근 가능해요!',
    //         width: '80%',
    //     }).then(function () {
    //         // navigate(`/SignIn`, {
    //         //   replace: true,
    //         // });
    //         window.location.replace('/SignIn');
    //     });
    // }
}
