# API Document

- [/auth](#get-auth)
- [/login](#post-login)
- [/logout](#post-logout)
- [/file](#get-filepath)
- [/document](#get-documentname)
- /group
  - /form
    - [/get](#get-groupformgetgroupnameformname)
    - [/submit](#post-groupformsubmitgroupnameformname)
  - /document
    - [/list](#get-groupdocumentlistname)
  - /user
    - [/list](#get-groupuserlistname)
- /user
  - /document
    - [/list](#get-userdocumentlist)
  - /group
    - [/list](#get-usergrouplist)

## データタイプ

### Date
日付。

```
yyyy/MM/dd HH:mm:ss
```

### FormDefineType

[フォームの取得](#get-groupformgetgroupnameformname) で使用する。

#### String

```
{
  "type": "string"
}
```

#### Check

```
{
  "type": "check"
  "element": Map<int, string> (チェック項目)
  "limit": int (チェック数の制限)
}
```

### FormSaveType

[データタイプ(FormSaveType)](#formsavevalue), [フォームの送信](#post-groupformsubmitgroupnameformname) で使用する。

#### String

```
{
  "type": "string",
  "content": string (内容)
}
```

#### Check

```
{
  "type": "check"
  "select": int[] (選択している項目)
}
```

### FormSaveValue

[フォームの取得](#get-groupformgetgroupnameformname) で使用する。

```
{
  "value": FormSaveType (項目の値)
  "comment": string (項目に対するコメント)
}
```

### FormGet

## `GET` `/auth`
ログインできているか確認する。

### Response

#### - StatusCode

| Code | Description |
|------|-------------|
| 200 OK | ログインしている。 |
| 401 Unauthorized | ログインしていない。 |

## `POST` `/auth`
ログインする。

### Request

#### - Basic 認証
学籍番号と `/login` で発行されたパスワード。

### Response

#### - StatusCode

| Code | Description |
|------|-------------|
| 200 OK | ログイン成功。 |

#### - Body `application/json`

```
{
  "auth": string
}
```

---

## `POST` `/login`
パスワードを指定の学籍番号に送信する。一度パスワードが生成されたら、5分で削除される。

### Request

#### - Body `application/json`

```
{
  "id": string (学籍番号)
}
```

### Response

#### - StatusCode

| Code | Description |
|------|-------------|
| 200 OK | メールが送信されました。 |
| 429 Too Many Requests | 既にメールが送信されている。 |
| 400 Bad Request | ボディが不十分。学籍番号が不正。 |

---

## `POST` `/logout`
ログアウトする。

### Response

#### - StatusCode

| Code | Description |
|------|-------------|
| 200 OK | ログアウト成功。 |

---

## `GET` `/file/{path...}`
指定パスのファイルを取得する。

### Request

#### - Parameter

| Name | Description |
|------|-------------|
| path | ファイルのパス。 |

### Response

#### - StatusCode

| Code | Description |
|------|-------------|
| 200 OK | ファイルの取得に成功。 |
| 400 Bad Request | パスが不正。 |
| 404 Not Found | ファイルが見つからなかった。 |

---

## `GET` `/document/{name}`
ドキュメントファイルを取得する。

### Permission

- GroupMember

### Request

#### - Parameter

| Name | Description |
|------|-------------|
| name | ドキュメント名。 |

### Response

#### - StatusCode

| Code | Description |
|------|-------------|
| 200 OK | ドキュメントの取得に成功。 |
| 400 Bad Request | ドキュメント名が指定されていない。 |
| 401 Unauthorized | ログインしていない。 |
| 404 Not Found | 存在しないドキュメント。もしくはアクセスできないドキュメント。 |

---

## `GET` `/group/form/get/{groupName}/{formName}`


### Permission

- GroupMember

### Request

#### - Parameter

| Name | Description |
|------|-------------|
| groupName | グループ名。 |
| formName | フォーム名。 |

### Response

#### - StatusCode

| Code | Description |
|------|-------------|
| 200 OK | フォームの取得に成功。 |
| 400 Bad Request | グループ名やフォーム名が指定されていない。 |
| 401 Unauthorized | ログインしていない。 |
| 404 Not Found | 存在しないフォーム。 |

#### - Body `application/json`

```
{
  "name": string (フォームの名前)
  "description": string (フォームの説明)
  "receive": Date (フォームを割り当てられた日付)
  "limit": Date (フォームの提出期限の日付)
  "update": Date (フォームが更新された日付)
  "values": Map<int, Value> (フォームの値一覧)
}

# Value
{
  "name": string (項目の名前)
  "description": string (項目の説明)
  "type": FormDefineType (項目のデータ型)
  "value": FormSaveValue? (項目のデータ)
}
```

---

## `POST` `/group/form/submit/{groupName}/{formName}`
フォームの送信を行う。

### Permission

- GroupMember

### Request

#### - Parameter

| Name | Description |
|------|-------------|
| groupName | グループ名。 |
| formName | フォーム名。 |

#### - Body `application/json`

```
{
  "values": Map<int, FormSaveType> (フォームの値一覧)
}
```

### Response

#### - StatusCode

| Code | Description |
|------|-------------|
| 200 OK | フォームの送信に成功。 |
| 400 Bad Request | グループ名やフォーム名が指定されていない。 |
| 401 Unauthorized | ログインしていない。 |
| 404 Not Found | 存在しないフォーム。 |

---

## `GET` `/group/document/list/{name}`
指定グループのドキュメント一覧を取得する。

### Permission

- GroupMember

### Request

#### - Parameter

| Name | Description |
|------|-------------|
| name | グループ名。 |

### Response

#### - StatusCode

| Code | Description |
|------|-------------|
| 200 OK | ドキュメント一覧の取得に成功。 |
| 400 Bad Request | グループ名が指定されていない。 |
| 401 Unauthorized | ログインしていない。 |
| 403 Forbidden | グループに属していない。 |

#### - Body `application/json`

```
{
  "document": string[] (ドキュメント一覧)
}
```

## `POST` `/group/document/list/{name}`
指定グループのドキュメント一覧を変更する。

### Permission

- Admin

### Request

#### - Parameter

| Name | Description |
|------|-------------|
| name | グループ名。 |

#### - Body `application/json`

```
{
  "document": string[] (ドキュメント一覧)
}
```

### Response

#### - StatusCode

| Code | Description |
|------|-------------|
| 200 OK | ドキュメント一覧の変更に成功。 |
| 400 Bad Request | グループ名が指定されていない。 |
| 401 Unauthorized | ログインしていない。 |
| 403 Forbidden | 編集権限がない。|

---

## `GET` `/group/user/list/{name}`
グループに属するユーザー一覧を取得する。

### Permission

- Admin
- GroupMember

### Request

#### - Parameter

| Name | Description |
|------|-------------|
| name | グループ名。 |

### Response

#### - StatusCode

| Code | Description |
|------|-------------|
| 200 OK | メンバー一覧の取得に成功。 |
| 400 Bad Request | グループ名が指定されていない。 |
| 401 Unauthorized | ログインしていない。 |
| 403 Forbidden | 取得権限がない。 |

#### - Body `application/json`

```
{
  "owner": string[] (オーナー一覧)
  "member": string[] (メンバー一覧)
}
```

## `POST` `/group/user/list/{name}`
グループに属するユーザー一覧を変更する。

### Permission

- Admin
- GroupOwner

### Request

#### - Parameter

| Name | Description |
|------|-------------|
| name | グループ名。 |

#### - Body `application/json`

```
{
  "owner": string[] (オーナー一覧 / Admin でなければ無視される)
  "member": string[] (メンバー一覧)
}
```

### Response

#### - StatusCode

| Code | Description |
|------|-------------|
| 200 OK | メンバー一覧の取得に成功。 |
| 400 Bad Request | グループ名が指定されていない。 |
| 401 Unauthorized | ログインしていない。 |
| 403 Forbidden | 編集権限がない。 |

---

## `GET` `/user/document/list`
ユーザーがアクセス可能なドキュメント一覧を取得する。

### Response

#### - StatusCode

| Code | Description |
|------|-------------|
| 200 OK | ドキュメント一覧の取得に成功。 |
| 401 Unauthorized | ログインしていない。 |

#### - Body `application/json`

```
{
  "document": string[] (ドキュメント一覧)
}
```

---

## `GET` `/user/group/list`
ユーザーが所属しているグループ一覧を取得する。

### Response

#### - StatusCode

| Code | Description |
|------|-------------|
| 200 OK | グループ一覧の取得に成功。 |
| 401 Unauthorized | ログインしていない。 |

#### - Body `application/json`

```
{
  "group": string[] (ドキュメント一覧)
}
```