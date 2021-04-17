# API Document

## `GET` `/auth`
ログインできているか確認します。

### Response

#### - StatusCode

| Code | Description |
|------|-------------|
| 200 OK | ログインしている |
| 401 Unauthorized | ログインしていない |

## `POST` `/auth`
ログインします。

### Request

#### - Basic 認証
学籍番号と `/login` で発行されたパスワード

### Response

#### - StatusCode

| Code | Description |
|------|-------------|
| 200 OK | ログイン成功 |

---

## `POST` `/login`

### Request

#### - Body `application/json`

```
{
  "id": string(学籍番号)
}
```

### Response

#### - StatusCode

| Code | Description |
|------|-------------|
| 200 OK | メールが送信されました |
| 429 Too Many Requests | 既にメールが送信されている |
| 400 Bad Request | ボディが不十分 |

---

## `POST` `/logout`
ログアウトします。

### Response

#### - StatusCode

| Code | Description |
|------|-------------|
| 200 OK | ログアウト成功 |

