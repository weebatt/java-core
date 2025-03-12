#### Проброс порта для БД

```bash
ssh -p 2222 -l s408116 -L5432:localhost:5432 helios.cs.ifmo.ru
```

```bash
psql -h localhost -d studs
```
