#!/bin/bash

PROJECT="computes/distributed-ai-or-something"

build_split(){
  local version="$1"
  local tag="$PROJECT:split-$version-arm" 
  echo "Building tag: $tag"

  docker build --tag "$tag" ./genetics-split
}

build_map(){
  local version="$1"
  local tag="$PROJECT:map-$version-arm"
  echo "Building tag: $tag"

  docker build --tag "$tag" ./genetics-map
}

build_reduce(){
  local version="$1"
  local tag="$PROJECT:reduce-$version-arm"
  echo "Building tag: $tag"

  docker build --tag "$tag" ./genetics-reduce
}

push_split(){
  local version="$1"
  local tag="$PROJECT:split-$version-arm" 
  echo "Pushing tag: $tag"

  docker push "$tag"
}

push_map(){
  local version="$1"
  local tag="$PROJECT:map-$version-arm"
  echo "Pushing tag: $tag"

  docker push "$tag"
}

push_reduce(){
  local version="$1"
  local tag="$PROJECT:reduce-$version-arm"
  echo "Pushing tag: $tag"

  docker push "$tag"
}

main(){
  local version="$1"

  build_split \
    && build_map \
    && build_reduce \
    && push_spit \
    && push_map \
    && push_reduce
}
main "$@"
