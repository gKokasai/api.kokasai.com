# api.kokasai.com

[![Kotlin](https://img.shields.io/badge/kotlin-1.5.0-blue.svg?logo=kotlin)](http://kotlinlang.org)
[![ktlint](https://img.shields.io/badge/code%20style-%E2%9D%A4-FF4081.svg)](https://ktlint.github.io/)
[![GitHub License](https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg?style=flat)](http://www.apache.org/licenses/LICENSE-2.0)

工華祭のバックエンドサーバー。

- [API Document](DOCUMENT.md)

## 開発

### コミットについて

#### コミットの前にすること
コミットをする前に、コードフォーマットを確認するために以下のコマンドを実行してください。

```
gradle ktlintCheck
```

#### コミットメッセージテンプレート

```
<種類>: <簡単な説明>
```

#### 種類

- **feat**: 機能の追加
- **change** 機能の変更
- **fix**: バグ修正
- **docs**: ドキュメントの変更
- **style**: 処理を変えていないコードの修正 (コードフォーマット等)
- **refactor**: バグ修正や機能追加を行わないコードの修正
- **perf**: パフォーマンスの改善
- **test**: テストの追加・修正
- **chore**: ライブラリの更新等の雑務
