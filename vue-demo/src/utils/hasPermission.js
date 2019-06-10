import store from '../store'

export function hasPermission(permission) {
    let myPermissions = store.getters.permissionList;
    return myPermissions.indexOf(permission) > -1;
}
