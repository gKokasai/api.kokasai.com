package com.kokasai.api.http

import com.kokasai.api.auth.UserLogin
import io.ktor.auth.authenticate
import io.ktor.http.HttpMethod
import io.ktor.routing.Routing
import io.ktor.routing.delete
import io.ktor.routing.get
import io.ktor.routing.method
import io.ktor.routing.post
import io.ktor.routing.route
import com.kokasai.api.http.form.assignGet as formAssignGet
import com.kokasai.api.http.form.assignPost as formAssignPost
import com.kokasai.api.http.form.listGet as formListGet
import com.kokasai.api.http.form.ownerGet as formOwnerGet
import com.kokasai.api.http.group.document.listGet as groupDocumentListGet
import com.kokasai.api.http.group.document.listPost as groupDocumentListPost
import com.kokasai.api.http.group.form.getGet as groupFormGetGet
import com.kokasai.api.http.group.form.listGet as groupFormListGet
import com.kokasai.api.http.group.form.submitPost as groupFormSubmitPost
import com.kokasai.api.http.group.listGet as groupListGet
import com.kokasai.api.http.group.user.listGet as groupUserListGet
import com.kokasai.api.http.group.user.listPost as groupUserListPost
import com.kokasai.api.http.logout.allPost as logoutAllPost
import com.kokasai.api.http.logout.indexPost as logoutIndexPost
import com.kokasai.api.http.user.document.listGet as userDocumentListGet
import com.kokasai.api.http.user.form.listGet as userFormListGet
import com.kokasai.api.http.user.group.listGet as userGroupListGet

/**
 * HTTP のルート設定をする
 */
fun Routing.httpRoute() {
    route("/") {
        get(indexGet)
    }
    route("/auth") {
        get(authGet)
        authenticate(UserLogin.authName) {
            post(authPost)
        }
    }
    route("/login") {
        post(loginPost)
    }
    route("/logout") {
        route("/") {
            post(logoutIndexPost)
        }
        route("/all") {
            post(logoutAllPost)
        }
    }
    route("/session") {
        get(sessionGet)
    }
    route("/document") {
        route("/{documentName}") {
            get(documentGet)
            post(documentPost)
        }
    }
    route("/webdav") {
        route("/{path...}") {
            get(webdavGet)
            post(webdavPost)
            delete(webdavDelete)
            method(HttpMethod("MKCOL")) {
                handle(webdavMkcol)
            }
        }
    }
    route("/form") {
        route("/assign") {
            route("/{formName}") {
                get(formAssignGet)
                post(formAssignPost)
            }
        }
        route("/list") {
            route("/{formName}") {
                get(formListGet)
            }
        }
        route("/owner") {
            get(formOwnerGet)
        }
    }
    route("/group") {
        route("/list") {
            get(groupListGet)
        }
        route("/form") {
            route("/list") {
                route("/{groupName}") {
                    get(groupFormListGet)
                }
            }
            route("/get") {
                route("/{groupName}/{formName}") {
                    get(groupFormGetGet)
                }
            }
            route("/submit") {
                route("/{groupName}/{formName}") {
                    post(groupFormSubmitPost)
                }
            }
        }
        route("/document") {
            route("/list") {
                route("/{groupName}") {
                    get(groupDocumentListGet)
                    post(groupDocumentListPost)
                }
            }
        }
        route("/user") {
            route("/list") {
                route("/{groupName}") {
                    get(groupUserListGet)
                    post(groupUserListPost)
                }
            }
        }
    }
    route("/user") {
        route("/form") {
            route("/list") {
                get(userFormListGet)
            }
        }
        route("/document") {
            route("/list") {
                get(userDocumentListGet)
            }
        }
        route("/group") {
            route("/list") {
                get(userGroupListGet)
            }
        }
    }
}
