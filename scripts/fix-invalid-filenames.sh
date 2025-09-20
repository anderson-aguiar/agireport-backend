#!/bin/bash

# Saia se qualquer comando falhar
set -e

# Mensagem de início
echo "🔍 Procurando arquivos com ':' no nome..."

# Lista de arquivos com dois-pontos
FILES=$(git ls-tree -r --name-only HEAD | grep ':' || true)

if [ -z "$FILES" ]; then
  echo "✅ Nenhum arquivo com ':' encontrado. Nada a fazer."
  exit 0
fi

# Loop por cada arquivo e renomeia
for FILE in $FILES; do
  NEW_FILE=$(echo "$FILE" | sed 's/:/-/g')
  echo "🚚 Renomeando: $FILE  →  $NEW_FILE"

  # Cria diretório de destino se necessário
  DIRNAME=$(dirname "$NEW_FILE")
  mkdir -p "$DIRNAME"

  # Move o arquivo no Git
  git mv "$FILE" "$NEW_FILE"
done

# Commit
echo "📦 Commitando mudanças..."
git commit -m "Renomeando arquivos com ':' para compatibilidade com Windows"

# Push
echo "🚀 Enviando para o repositório remoto..."
git push

echo "✅ Concluído com sucesso!"
