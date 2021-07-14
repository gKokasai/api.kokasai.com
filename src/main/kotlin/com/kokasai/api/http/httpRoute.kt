package com.kokasai.api.http

import com.kokasai.api.auth.UserLogin
import io.ktor.auth.authenticate
import io.ktor.routing.Route
import io.ktor.routing.Routing
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.route
import com.kokasai.api.http.form.assignGet as formAssignGet
import com.kokasai.api.http.form.assignPost as formAssignPost
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
    operator fun String.invoke(build: Route.() -> Unit) = route(this, build)

    "/" {
        get(indexGet)
    }
    "/auth" {
        get(authGet)
        authenticate(UserLogin.authName) {
            post(authPost)
        }
    }
    "/login" {
        post(loginPost)
    }
    "/logout" {
        "/" {
            post(logoutIndexPost)
        }
        "/all" {
            post(logoutAllPost)
        }
    }
    "/session" {
        get(sessionGet)
    }
    "/document" {
        "/{documentName}" {
            get(documentGet)
            post(documentPost)
        }
    }
    "/form" {
        "/assign" {
            "/{formName}" {
                get(formAssignGet)
                post(formAssignPost)
            }
        }
    }
    "/group" {
        "/list" {
            get(groupListGet)
        }
        "/form" {
            "/list" {
                "/{groupName}" {
                    get(groupFormListGet)
                }
            }
            "/get" {
                "/{groupName}/{formName}" {
                    get(groupFormGetGet)
                }
            }
            "/submit" {
                "/{groupName}/{formName}" {
                    post(groupFormSubmitPost)
                }
            }
        }
        "/document" {
            "/list" {
                "/{groupName}" {
                    get(groupDocumentListGet)
                    post(groupDocumentListPost)
                }
            }
        }
        "/user" {
            "/list" {
                "/{groupName}" {
                    get(groupUserListGet)
                    post(groupUserListPost)
                }
            }
        }
    }
    "/user" {
        "/form" {
            "/list" {
                get(userFormListGet)
            }
        }
        "/document" {
            "/list" {
                get(userDocumentListGet)
            }
        }
        "/group" {
            "/list" {
                get(userGroupListGet)
            }
        }
    }
}
