/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.sola.android.architecture.data.entity;

import com.google.gson.annotations.SerializedName;

/**
 * User Entity used in the data layer.
 */
public class UserEntity {

  @SerializedName("id")
  private int userId;

  @SerializedName("cover_url")
  private String coverUrl;

  @SerializedName("full_name")
  private String fullname;

  @SerializedName("description")
  private String description;

  @SerializedName("followers")
  private int followers;

  @SerializedName("email")
  private String email;

  public UserEntity() {
    //empty
  }

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public String getCoverUrl() {
    return coverUrl;
  }

  public void setCoverUrl(String coverUrl) {
    this.coverUrl = coverUrl;
  }

  public String getFullname() {
    return fullname;
  }

  public void setFullname(String fullname) {
    this.fullname = fullname;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public int getFollowers() {
    return followers;
  }

  public void setFollowers(int followers) {
    this.followers = followers;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Override
  public String toString() {

    return "***** User Entity Details *****\n" + "id=" + this.getUserId() + "\n" + "cover url=" + this.getCoverUrl() + "\n" + "fullname=" + this.getFullname() + "\n" + "email=" + this.getEmail() + "\n" + "description=" + this.getDescription() + "\n" + "followers=" + this.getFollowers() + "\n" + "*******************************";
  }
}
