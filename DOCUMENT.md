# API Document

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
| 400 Bad Request | ボディが不十分。 |

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

## `GET` `/group/document/list/{name}`
指定グループのドキュメント一覧を取得する。

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
| 403 Forbidden | グループに属していない。もしくは編集権限がない。|

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